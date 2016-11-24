package org.appsugar.data.mongo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = -2092826703L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerson person = new QPerson("person");

    public final org.appsugar.bean.entity.QStringIdEntity _super = new org.appsugar.bean.entity.QStringIdEntity(this);

    public final QAddress address;

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath firstname = createString("firstname");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath lastname = createString("lastname");

    public final ListPath<Pet, QPet> pets = this.<Pet, QPet>createList("pets", Pet.class, QPet.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QPerson(String variable) {
        this(Person.class, forVariable(variable), INITS);
    }

    public QPerson(Path<? extends Person> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPerson(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPerson(PathMetadata metadata, PathInits inits) {
        this(Person.class, metadata, inits);
    }

    public QPerson(Class<? extends Person> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
    }

}

