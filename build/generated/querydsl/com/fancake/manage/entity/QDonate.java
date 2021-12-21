package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDonate is a Querydsl query type for Donate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDonate extends EntityPathBase<Donate> {

    private static final long serialVersionUID = -1851341375L;

    public static final QDonate donate = new QDonate("donate");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath donatename = createString("donatename");

    public final StringPath donateregdate = createString("donateregdate");

    public final NumberPath<Integer> donateseq = createNumber("donateseq", Integer.class);

    public final NumberPath<Integer> donateuseyn = createNumber("donateuseyn", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QDonate(String variable) {
        super(Donate.class, forVariable(variable));
    }

    public QDonate(Path<? extends Donate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDonate(PathMetadata metadata) {
        super(Donate.class, metadata);
    }

}

