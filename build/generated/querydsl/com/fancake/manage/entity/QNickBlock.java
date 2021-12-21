package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNickBlock is a Querydsl query type for NickBlock
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNickBlock extends EntityPathBase<NickBlock> {

    private static final long serialVersionUID = -1034662312L;

    public static final QNickBlock nickBlock = new QNickBlock("nickBlock");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final StringPath mbnick = createString("mbnick");

    public final NumberPath<Integer> mbno = createNumber("mbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QNickBlock(String variable) {
        super(NickBlock.class, forVariable(variable));
    }

    public QNickBlock(Path<? extends NickBlock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNickBlock(PathMetadata metadata) {
        super(NickBlock.class, metadata);
    }

}

