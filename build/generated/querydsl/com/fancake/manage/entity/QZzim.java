package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QZzim is a Querydsl query type for Zzim
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QZzim extends EntityPathBase<Zzim> {

    private static final long serialVersionUID = 1133933622L;

    public static final QZzim zzim = new QZzim("zzim");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final NumberPath<Integer> itemseq = createNumber("itemseq", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath zzimdate = createString("zzimdate");

    public final NumberPath<Integer> zzimitemseq = createNumber("zzimitemseq", Integer.class);

    public final NumberPath<Integer> zzimmbno = createNumber("zzimmbno", Integer.class);

    public QZzim(String variable) {
        super(Zzim.class, forVariable(variable));
    }

    public QZzim(Path<? extends Zzim> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZzim(PathMetadata metadata) {
        super(Zzim.class, metadata);
    }

}

