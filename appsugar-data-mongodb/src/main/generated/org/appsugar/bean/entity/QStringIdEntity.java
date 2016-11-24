package org.appsugar.bean.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStringIdEntity is a Querydsl query type for StringIdEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QStringIdEntity extends BeanPath<StringIdEntity> {

    private static final long serialVersionUID = 2044173739L;

    public static final QStringIdEntity stringIdEntity = new QStringIdEntity("stringIdEntity");

    public final QGenericIdEntity _super = new QGenericIdEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath id = createString("id");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QStringIdEntity(String variable) {
        super(StringIdEntity.class, forVariable(variable));
    }

    public QStringIdEntity(Path<? extends StringIdEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStringIdEntity(PathMetadata metadata) {
        super(StringIdEntity.class, metadata);
    }

}

