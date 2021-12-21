package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1133421285L;

    public static final QItem item = new QItem("item");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath itemimg1 = createString("itemimg1");

    public final StringPath itemname = createString("itemname");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final StringPath itemselladdprice = createString("itemselladdprice");

    public final StringPath itemsellprice = createString("itemsellprice");

    public final NumberPath<Integer> itemseq = createNumber("itemseq", Integer.class);

    public final StringPath itemstartprice = createString("itemstartprice");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

