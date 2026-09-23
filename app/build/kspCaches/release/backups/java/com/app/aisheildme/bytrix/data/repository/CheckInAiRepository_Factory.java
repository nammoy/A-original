package com.app.aisheildme.bytrix.data.repository;

import com.app.aisheildme.bytrix.data.ai.OnDeviceAi;
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
public final class CheckInAiRepository_Factory implements Factory<CheckInAiRepository> {
  private final Provider<OnDeviceAi> onDeviceAiProvider;

  public CheckInAiRepository_Factory(Provider<OnDeviceAi> onDeviceAiProvider) {
    this.onDeviceAiProvider = onDeviceAiProvider;
  }

  @Override
  public CheckInAiRepository get() {
    return newInstance(onDeviceAiProvider.get());
  }

  public static CheckInAiRepository_Factory create(Provider<OnDeviceAi> onDeviceAiProvider) {
    return new CheckInAiRepository_Factory(onDeviceAiProvider);
  }

  public static CheckInAiRepository newInstance(OnDeviceAi onDeviceAi) {
    return new CheckInAiRepository(onDeviceAi);
  }
}
