package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFaq is a Querydsl query type for Faq
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFaq extends EntityPathBase<Faq> {

    private static final long serialVersionUID = -1487462140L;

    public static final QFaq faq = new QFaq("faq");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath facontent = createString("facontent");

    public final NumberPath<Integer> faid = createNumber("faid", Integer.class);

    public final NumberPath<Integer> faorder = createNumber("faorder", Integer.class);

    public final StringPath faregdate = createString("faregdate");

    public final StringPath fasubject = createString("fasubject");

    public final NumberPath<Integer> fmid = createNumber("fmid", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QFaq(String variable) {
        super(Faq.class, forVariable(variable));
    }

    public QFaq(Path<? extends Faq> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaq(PathMetadata metadata) {
        super(Faq.class, metadata);
    }

}

