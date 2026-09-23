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
public final class PatientReviewPdfGenerator_Factory implements Factory<PatientReviewPdfGenerator> {
  private final Provider<Context> contextProvider;

  public PatientReviewPdfGenerator_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PatientReviewPdfGenerator get() {
    return newInstance(contextProvider.get());
  }

  public static PatientReviewPdfGenerator_Factory create(Provider<Context> contextProvider) {
    return new PatientReviewPdfGenerator_Factory(contextProvider);
  }

  public static PatientReviewPdfGenerator newInstance(Context context) {
    return new PatientReviewPdfGenerator(context);
  }
}
