package org.appsugar.bean.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenericIdEntity is a Querydsl query type for GenericIdEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGenericIdEntity extends BeanPath<GenericIdEntity<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -302671431L;

    public static final QGenericIdEntity genericIdEntity = new QGenericIdEntity("genericIdEntity");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final SimplePath<java.io.Serializable> id = createSimple("id", java.io.Serializable.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QGenericIdEntity(String variable) {
        super((Class) GenericIdEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QGenericIdEntity(Path<? extends GenericIdEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QGenericIdEntity(PathMetadata metadata) {
        super((Class) GenericIdEntity.class, metadata);
    }

}

