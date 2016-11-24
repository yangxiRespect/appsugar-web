package org.appsugar.bean.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLongIdEntity is a Querydsl query type for LongIdEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QLongIdEntity extends BeanPath<LongIdEntity> {

    private static final long serialVersionUID = 1814831126L;

    public static final QLongIdEntity longIdEntity = new QLongIdEntity("longIdEntity");

    public final QGenericIdEntity _super = new QGenericIdEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QLongIdEntity(String variable) {
        super(LongIdEntity.class, forVariable(variable));
    }

    public QLongIdEntity(Path<? extends LongIdEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLongIdEntity(PathMetadata metadata) {
        super(LongIdEntity.class, metadata);
    }

}

