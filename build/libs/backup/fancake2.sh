#!/bin/bash
#################################################################### 
#
#       Startup/shutdown script Process Script 
#
# chkconfig	: 
# description	: Process Run/Stop/Status Script for Monitoring   
#              
# project Name	:  
# sc version 	: 1.0.0 
# 
#################################################################### 

# ==========================================================================
#
# Set to the script for application running configuration !!   
#
# ==========================================================================#
SERVICE_NAME=fancake2
SERVICE_VER=0.0.1
SERVICE_HOME=/web/fancake2
OPTS="-Xmx512m"
SERVICE_JAR=$SERVICE_HOME/bin/$SERVICE_NAME-$SERVICE_VER.jar
PID_PATH_NAME=$SERVICE_HOME/bin/$SERVICE_NAME.pid

# ==========================================================================
#
# >> Start script source    
#
# ==========================================================================

cd $SERVICE_HOME/bin
STATUS="NA"

getPsPid()
{
	APID=`ps ax | grep -v grep | grep -v bash | grep -v ssh | grep java | grep jar | grep $SERVICE_NAME | awk '{print $1}' `
	#echo "APID [$APID]"
}

getFilePid()
{
        if [  -f $PID_PATH_NAME ]; then
        	FPID=$(cat $PID_PATH_NAME);
		#echo "FPID [$FPID]"
	else
		FPID=''
	fi
}

status()
{
	getFilePid
	getPsPid
	if [ -z "$APID" ]; then
		STATUS="DOWN"
		if [ ! -z "$FPID" ]; then
			rm -f $PID_PATH_NAME
		fi
    	else
        	if [ "$FPID" == "$APID" ]; then
			STATUS="ALIVE"
		else
			STATUS="ERROR"
			echo " [$STATUS] $SERVICE_NAME : Check File Pid [$FPID] / Ps Pid [$APID] "
		fi
	fi
	if [ "$1" = true ]; then
		echo " [$STATUS] $SERVICE_NAME"
	fi
}

start()
{
	status
	if [ "$STATUS" == "DOWN" ]; then
		echo -n "$SERVICE_NAME starting"
		exec java -jar $OPTS $SERVICE_JAR >> /dev/null &
		echo $! > $PID_PATH_NAME
		for sleepcnt in 1 2 3 4 5
		do
			sleep 1
  			echo -n "."
		done
		local i=1
		while [ "$STATUS" != "ALIVE" -a $i -lt 5 ]
		do
			sleep 1
			status
			i=$(($i+1))
			echo -n "."
		done
		echo
        	if [ "$STATUS" == "ALIVE" ]; then
            		echo "$SERVICE_NAME start success"
            		return
        	else
            		echo "$SERVICE_NAME start fail"
            		exit 9
        	fi
	fi
	if [ "$STATUS" == "ALIVE" ]; then
		echo "$SERVICE_NAME aready started"
		exit 9
	fi
	if [ "$STATUS" == "ERROR" ]; then
		echo "$SERVICE_NAME start fail"
        	exit 9
    	fi
}
stop()
{
	status
	if [ "$STATUS" == "ALIVE" ]; then
		echo -n "$SERVICE_NAME stopping"
		kill $FPID;
		rm -f $PID_PATH_NAME  
        	local i=1
                while [ "$STATUS" != "DOWN" -a $i -lt 10 ]
                do
                        sleep 1
                        status
                        i=$(($i+1))
                        echo -n "."
                done
		echo
	      	if [ "$STATUS" == "DOWN" ]; then
            		echo "$SERVICE_NAME stop success"
			return
        	else
            		echo "$SERVICE_NAME stop fail"
            		exit 9
        	fi
    	fi
    	if [ "$STATUS" == "DOWN" ]; then
        	echo "$SERVICE_NAME aready stoped"
	fi
	if [ "$STATUS" == "ERROR" ]; then
        	echo "$SERVICE_NAME stop fail"
	fi
}

# ==========================================================================
#
# >> Operation script case   
#
# ==========================================================================

case $1 in
    start)
	start
    ;;
    status)
	status true
    ;;
    stop)
	stop
    ;;
    restart)
	stop
	start
    ;;
    *)
       echo "Help";
       echo "$0 { start | status | stop | restart } ";	
    ;;
esac
