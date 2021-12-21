package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInfluence is a Querydsl query type for Influence
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInfluence extends EntityPathBase<Influence> {

    private static final long serialVersionUID = -2020144541L;

    public static final QInfluence influence = new QInfluence("influence");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath actafreeca = createString("actafreeca");

    public final StringPath actbroadcast = createString("actbroadcast");

    public final StringPath actinstagram = createString("actinstagram");

    public final StringPath acttwitch = createString("acttwitch");

    public final StringPath actwriter = createString("actwriter");

    public final StringPath actyoutube = createString("actyoutube");

    public final StringPath coverimg1 = createString("coverimg1");

    public final StringPath coverimg2 = createString("coverimg2");

    public final StringPath coverimg3 = createString("coverimg3");

    public final StringPath introduction = createString("introduction");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final NumberPath<Integer> mbno = createNumber("mbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath reqdate = createString("reqdate");

    public final StringPath tempnick = createString("tempnick");

    public final StringPath tempprofile = createString("tempprofile");

    public QInfluence(String variable) {
        super(Influence.class, forVariable(variable));
    }

    public QInfluence(Path<? extends Influence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInfluence(PathMetadata metadata) {
        super(Influence.class, metadata);
    }

}

