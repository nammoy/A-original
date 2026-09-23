package com.app.aisheildme.bytrix.data.ai;

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
public final class OnDeviceAi_Factory implements Factory<OnDeviceAi> {
  @Override
  public OnDeviceAi get() {
    return newInstance();
  }

  public static OnDeviceAi_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OnDeviceAi newInstance() {
    return new OnDeviceAi();
  }

  private static final class InstanceHolder {
    static final OnDeviceAi_Factory INSTANCE = new OnDeviceAi_Factory();
  }
}
