package com.app.aisheildme.bytrix.feature.checkin;

import com.app.aisheildme.bytrix.core.common.DetailHandoff;
import com.app.aisheildme.bytrix.core.util.LocalNotifier;
import com.app.aisheildme.bytrix.data.pdf.PatientReviewPdfGenerator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class CheckInReportViewModel_Factory implements Factory<CheckInReportViewModel> {
  private final Provider<PatientReviewPdfGenerator> pdfGeneratorProvider;

  private final Provider<LocalNotifier> notifierProvider;

  private final Provider<DetailHandoff> handoffProvider;

  public CheckInReportViewModel_Factory(Provider<PatientReviewPdfGenerator> pdfGeneratorProvider,
      Provider<LocalNotifier> notifierProvider, Provider<DetailHandoff> handoffProvider) {
    this.pdfGeneratorProvider = pdfGeneratorProvider;
    this.notifierProvider = notifierProvider;
    this.handoffProvider = handoffProvider;
  }

  @Override
  public CheckInReportViewModel get() {
    return newInstance(pdfGeneratorProvider.get(), notifierProvider.get(), handoffProvider.get());
  }

  public static CheckInReportViewModel_Factory create(
      Provider<PatientReviewPdfGenerator> pdfGeneratorProvider,
      Provider<LocalNotifier> notifierProvider, Provider<DetailHandoff> handoffProvider) {
    return new CheckInReportViewModel_Factory(pdfGeneratorProvider, notifierProvider, handoffProvider);
  }

  public static CheckInReportViewModel newInstance(PatientReviewPdfGenerator pdfGenerator,
      LocalNotifier notifier, DetailHandoff handoff) {
    return new CheckInReportViewModel(pdfGenerator, notifier, handoff);
  }
}
