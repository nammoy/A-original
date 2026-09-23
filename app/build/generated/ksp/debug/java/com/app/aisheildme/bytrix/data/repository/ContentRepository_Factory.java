package com.app.aisheildme.bytrix.data.repository;

import com.app.aisheildme.bytrix.data.remote.api.MiscApi;
import com.app.aisheildme.bytrix.data.remote.api.PagesApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class ContentRepository_Factory implements Factory<ContentRepository> {
  private final Provider<PagesApi> pagesApiProvider;

  private final Provider<MiscApi> miscApiProvider;

  public ContentRepository_Factory(Provider<PagesApi> pagesApiProvider,
      Provider<MiscApi> miscApiProvider) {
    this.pagesApiProvider = pagesApiProvider;
    this.miscApiProvider = miscApiProvider;
  }

  @Override
  public ContentRepository get() {
    return newInstance(pagesApiProvider.get(), miscApiProvider.get());
  }

  public static ContentRepository_Factory create(Provider<PagesApi> pagesApiProvider,
      Provider<MiscApi> miscApiProvider) {
    return new ContentRepository_Factory(pagesApiProvider, miscApiProvider);
  }

  public static ContentRepository newInstance(PagesApi pagesApi, MiscApi miscApi) {
    return new ContentRepository(pagesApi, miscApi);
  }
}
