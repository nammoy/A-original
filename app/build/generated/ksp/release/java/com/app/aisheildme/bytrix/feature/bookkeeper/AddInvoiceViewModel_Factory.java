package com.app.aisheildme.bytrix.feature.bookkeeper;

import com.app.aisheildme.bytrix.data.repository.ContentRepository;
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
public final class AddInvoiceViewModel_Factory implements Factory<AddInvoiceViewModel> {
  private final Provider<ContentRepository> contentRepositoryProvider;

  public AddInvoiceViewModel_Factory(Provider<ContentRepository> contentRepositoryProvider) {
    this.contentRepositoryProvider = contentRepositoryProvider;
  }

  @Override
  public AddInvoiceViewModel get() {
    return newInstance(contentRepositoryProvider.get());
  }

  public static AddInvoiceViewModel_Factory create(
      Provider<ContentRepository> contentRepositoryProvider) {
    return new AddInvoiceViewModel_Factory(contentRepositoryProvider);
  }

  public static AddInvoiceViewModel newInstance(ContentRepository contentRepository) {
    return new AddInvoiceViewModel(contentRepository);
  }
}
