package com.app.aisheildme.bytrix.core.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LocalNotifier_Factory implements Factory<LocalNotifier> {
  private final Provider<Context> contextProvider;

  public LocalNotifier_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalNotifier get() {
    return newInstance(contextProvider.get());
  }

  public static LocalNotifier_Factory create(Provider<Context> contextProvider) {
    return new LocalNotifier_Factory(contextProvider);
  }

  public static LocalNotifier newInstance(Context context) {
    return new LocalNotifier(context);
  }
}
