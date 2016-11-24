package org.appsugar.data.mongo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPet is a Querydsl query type for Pet
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPet extends BeanPath<Pet> {

    private static final long serialVersionUID = -523695549L;

    public static final QPet pet = new QPet("pet");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath name = createString("name");

    public QPet(String variable) {
        super(Pet.class, forVariable(variable));
    }

    public QPet(Path<? extends Pet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPet(PathMetadata metadata) {
        super(Pet.class, metadata);
    }

}

