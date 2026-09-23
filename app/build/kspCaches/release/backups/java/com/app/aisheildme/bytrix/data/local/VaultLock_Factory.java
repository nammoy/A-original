package com.app.aisheildme.bytrix.data.local;

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
public final class VaultLock_Factory implements Factory<VaultLock> {
  @Override
  public VaultLock get() {
    return newInstance();
  }

  public static VaultLock_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static VaultLock newInstance() {
    return new VaultLock();
  }

  private static final class InstanceHolder {
    static final VaultLock_Factory INSTANCE = new VaultLock_Factory();
  }
}
