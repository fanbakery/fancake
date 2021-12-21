package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberAlarm is a Querydsl query type for MemberAlarm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberAlarm extends EntityPathBase<MemberAlarm> {

    private static final long serialVersionUID = 2137282501L;

    public static final QMemberAlarm memberAlarm = new QMemberAlarm("memberAlarm");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final StringPath mbalarmmsg = createString("mbalarmmsg");

    public final StringPath mbalarmopenyn = createString("mbalarmopenyn");

    public final NumberPath<Integer> mbalarmseq = createNumber("mbalarmseq", Integer.class);

    public final StringPath mbinfulencerno = createString("mbinfulencerno");

    public final NumberPath<Integer> mbno = createNumber("mbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QMemberAlarm(String variable) {
        super(MemberAlarm.class, forVariable(variable));
    }

    public QMemberAlarm(Path<? extends MemberAlarm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberAlarm(PathMetadata metadata) {
        super(MemberAlarm.class, metadata);
    }

}

