package com.app.aisheildme.bytrix.core.common;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DetailHandoff_Factory implements Factory<DetailHandoff> {
  @Override
  public DetailHandoff get() {
    return newInstance();
  }

  public static DetailHandoff_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DetailHandoff newInstance() {
    return new DetailHandoff();
  }

  private static final class InstanceHolder {
    static final DetailHandoff_Factory INSTANCE = new DetailHandoff_Factory();
  }
}
