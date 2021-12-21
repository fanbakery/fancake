package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeen is a Querydsl query type for Seen
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSeen extends EntityPathBase<Seen> {

    private static final long serialVersionUID = 1133704781L;

    public static final QSeen seen = new QSeen("seen");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final StringPath itemseendate = createString("itemseendate");

    public final NumberPath<Integer> itemseq = createNumber("itemseq", Integer.class);

    public final NumberPath<Integer> mbno = createNumber("mbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> seenseq = createNumber("seenseq", Integer.class);

    public QSeen(String variable) {
        super(Seen.class, forVariable(variable));
    }

    public QSeen(Path<? extends Seen> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeen(PathMetadata metadata) {
        super(Seen.class, metadata);
    }

}

