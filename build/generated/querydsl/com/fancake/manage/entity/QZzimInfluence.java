package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QZzimInfluence is a Querydsl query type for ZzimInfluence
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QZzimInfluence extends EntityPathBase<ZzimInfluence> {

    private static final long serialVersionUID = -254400673L;

    public static final QZzimInfluence zzimInfluence = new QZzimInfluence("zzimInfluence");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> influenmbno = createNumber("influenmbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath zzimdate = createString("zzimdate");

    public final NumberPath<Integer> zziminfluenseq = createNumber("zziminfluenseq", Integer.class);

    public final NumberPath<Integer> zzimmbno = createNumber("zzimmbno", Integer.class);

    public QZzimInfluence(String variable) {
        super(ZzimInfluence.class, forVariable(variable));
    }

    public QZzimInfluence(Path<? extends ZzimInfluence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZzimInfluence(PathMetadata metadata) {
        super(ZzimInfluence.class, metadata);
    }

}

