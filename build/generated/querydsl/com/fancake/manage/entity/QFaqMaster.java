package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFaqMaster is a Querydsl query type for FaqMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFaqMaster extends EntityPathBase<FaqMaster> {

    private static final long serialVersionUID = -1807391610L;

    public static final QFaqMaster faqMaster = new QFaqMaster("faqMaster");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath fmheadhtml = createString("fmheadhtml");

    public final NumberPath<Integer> fmid = createNumber("fmid", Integer.class);

    public final StringPath fmmobileheadhtml = createString("fmmobileheadhtml");

    public final StringPath fmmobiletailhtml = createString("fmmobiletailhtml");

    public final NumberPath<Integer> fmorder = createNumber("fmorder", Integer.class);

    public final StringPath fmsubject = createString("fmsubject");

    public final StringPath fmtailhtml = createString("fmtailhtml");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QFaqMaster(String variable) {
        super(FaqMaster.class, forVariable(variable));
    }

    public QFaqMaster(Path<? extends FaqMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaqMaster(PathMetadata metadata) {
        super(FaqMaster.class, metadata);
    }

}

