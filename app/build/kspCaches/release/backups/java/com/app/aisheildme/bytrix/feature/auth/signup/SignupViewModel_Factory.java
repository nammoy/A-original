package com.app.aisheildme.bytrix.feature.auth.signup;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class SignupViewModel_Factory implements Factory<SignupViewModel> {
  @Override
  public SignupViewModel get() {
    return newInstance();
  }

  public static SignupViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SignupViewModel newInstance() {
    return new SignupViewModel();
  }

  private static final class InstanceHolder {
    static final SignupViewModel_Factory INSTANCE = new SignupViewModel_Factory();
  }
}
