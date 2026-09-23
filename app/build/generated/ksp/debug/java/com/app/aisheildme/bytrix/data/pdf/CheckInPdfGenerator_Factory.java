package com.app.aisheildme.bytrix.data.pdf;

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
public final class CheckInPdfGenerator_Factory implements Factory<CheckInPdfGenerator> {
  private final Provider<Context> contextProvider;

  public CheckInPdfGenerator_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CheckInPdfGenerator get() {
    return newInstance(contextProvider.get());
  }

  public static CheckInPdfGenerator_Factory create(Provider<Context> contextProvider) {
    return new CheckInPdfGenerator_Factory(contextProvider);
  }

  public static CheckInPdfGenerator newInstance(Context context) {
    return new CheckInPdfGenerator(context);
  }
}
