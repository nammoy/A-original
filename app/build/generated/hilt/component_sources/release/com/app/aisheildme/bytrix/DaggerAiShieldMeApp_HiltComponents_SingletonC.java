package com.app.aisheildme.bytrix;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.app.aisheildme.bytrix.core.common.DetailHandoff;
import com.app.aisheildme.bytrix.core.util.BiometricAvailability;
import com.app.aisheildme.bytrix.core.util.FileOpener;
import com.app.aisheildme.bytrix.core.util.LocalNotifier;
import com.app.aisheildme.bytrix.data.FirebaseDeviceTokenProvider;
import com.app.aisheildme.bytrix.data.ai.DocumentTextReader;
import com.app.aisheildme.bytrix.data.ai.OnDeviceAi;
import com.app.aisheildme.bytrix.data.local.SessionStore;
import com.app.aisheildme.bytrix.data.local.VaultLock;
import com.app.aisheildme.bytrix.data.local.db.AppDatabase;
import com.app.aisheildme.bytrix.data.local.db.CheckInHistoryDao;
import com.app.aisheildme.bytrix.data.pdf.PatientReviewPdfGenerator;
import com.app.aisheildme.bytrix.data.remote.api.AuthApi;
import com.app.aisheildme.bytrix.data.remote.api.DocumentApi;
import com.app.aisheildme.bytrix.data.remote.api.MiscApi;
import com.app.aisheildme.bytrix.data.remote.api.NotificationApi;
import com.app.aisheildme.bytrix.data.remote.api.PagesApi;
import com.app.aisheildme.bytrix.data.remote.api.PatientProfileApi;
import com.app.aisheildme.bytrix.data.remote.api.RecordApi;
import com.app.aisheildme.bytrix.data.repository.AuthRepository;
import com.app.aisheildme.bytrix.data.repository.CheckInAiRepository;
import com.app.aisheildme.bytrix.data.repository.CheckInHistoryRepository;
import com.app.aisheildme.bytrix.data.repository.ContentRepository;
import com.app.aisheildme.bytrix.data.repository.DocumentRepository;
import com.app.aisheildme.bytrix.data.repository.NotificationRepository;
import com.app.aisheildme.bytrix.data.repository.PatientProfileRepository;
import com.app.aisheildme.bytrix.data.repository.RecordRepository;
import com.app.aisheildme.bytrix.di.DataModule_ProvideCheckInHistoryDaoFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvideDatabaseFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvideDocumentApiFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvideMiscApiFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvideNotificationApiFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvidePagesApiFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvidePatientProfileApiFactory;
import com.app.aisheildme.bytrix.di.DataModule_ProvideRecordApiFactory;
import com.app.aisheildme.bytrix.di.NetworkModule_ProvideAuthApiFactory;
import com.app.aisheildme.bytrix.di.NetworkModule_ProvideJsonFactory;
import com.app.aisheildme.bytrix.di.NetworkModule_ProvideOkHttpFactory;
import com.app.aisheildme.bytrix.di.NetworkModule_ProvideRetrofitFactory;
import com.app.aisheildme.bytrix.di.NetworkModule_ProvideSessionStoreFactory;
import com.app.aisheildme.bytrix.feature.auth.basicinfo.BasicInfoViewModel;
import com.app.aisheildme.bytrix.feature.auth.basicinfo.BasicInfoViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.basicinfo.BasicInfoViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.basicinfo.BasicInfoViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.login.LoginViewModel;
import com.app.aisheildme.bytrix.feature.auth.login.LoginViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.login.LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.login.LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.resetpassword.ResetPasswordViewModel;
import com.app.aisheildme.bytrix.feature.auth.resetpassword.ResetPasswordViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.resetpassword.ResetPasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.resetpassword.ResetPasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.setupprofile.SetupProfileViewModel;
import com.app.aisheildme.bytrix.feature.auth.setupprofile.SetupProfileViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.setupprofile.SetupProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.setupprofile.SetupProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.signup.SignupViewModel;
import com.app.aisheildme.bytrix.feature.auth.signup.SignupViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.signup.SignupViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.signup.SignupViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.splash.SplashViewModel;
import com.app.aisheildme.bytrix.feature.auth.splash.SplashViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.splash.SplashViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.splash.SplashViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.vaultpin.CreateVaultPinViewModel;
import com.app.aisheildme.bytrix.feature.auth.vaultpin.CreateVaultPinViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.vaultpin.CreateVaultPinViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.vaultpin.CreateVaultPinViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.verifyemail.VerifyEmailViewModel;
import com.app.aisheildme.bytrix.feature.auth.verifyemail.VerifyEmailViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.auth.verifyemail.VerifyEmailViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.auth.verifyemail.VerifyEmailViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.banking.BankingViewModel;
import com.app.aisheildme.bytrix.feature.banking.BankingViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.banking.BankingViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.banking.BankingViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.bookkeeper.AddInvoiceViewModel;
import com.app.aisheildme.bytrix.feature.bookkeeper.AddInvoiceViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.bookkeeper.AddInvoiceViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.bookkeeper.AddInvoiceViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkin.CheckInFormViewModel;
import com.app.aisheildme.bytrix.feature.checkin.CheckInFormViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.checkin.CheckInFormViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkin.CheckInFormViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkin.CheckInPreviewViewModel;
import com.app.aisheildme.bytrix.feature.checkin.CheckInPreviewViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.checkin.CheckInPreviewViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkin.CheckInPreviewViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkin.CheckInReportViewModel;
import com.app.aisheildme.bytrix.feature.checkin.CheckInReportViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.checkin.CheckInReportViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkin.CheckInReportViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkinhistory.CheckInHistoryViewModel;
import com.app.aisheildme.bytrix.feature.checkinhistory.CheckInHistoryViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.checkinhistory.CheckInHistoryViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.checkinhistory.CheckInHistoryViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.documents.DocumentsViewModel;
import com.app.aisheildme.bytrix.feature.documents.DocumentsViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.documents.DocumentsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.documents.DocumentsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.folders.FoldersViewModel;
import com.app.aisheildme.bytrix.feature.folders.FoldersViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.folders.FoldersViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.folders.FoldersViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.healthcare.HealthcareViewModel;
import com.app.aisheildme.bytrix.feature.healthcare.HealthcareViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.healthcare.HealthcareViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.healthcare.HealthcareViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.home.HomeViewModel;
import com.app.aisheildme.bytrix.feature.home.HomeViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.home.HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.home.HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.notification.NotificationViewModel;
import com.app.aisheildme.bytrix.feature.notification.NotificationViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.notification.NotificationViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.notification.NotificationViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.AvailabilityViewModel;
import com.app.aisheildme.bytrix.feature.profile.AvailabilityViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.profile.AvailabilityViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.AvailabilityViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.EditProfileViewModel;
import com.app.aisheildme.bytrix.feature.profile.EditProfileViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.profile.EditProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.EditProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.ProfileViewModel;
import com.app.aisheildme.bytrix.feature.profile.ProfileViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.profile.ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.ResetPinViewModel;
import com.app.aisheildme.bytrix.feature.profile.ResetPinViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.profile.ResetPinViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.ResetPinViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.StaticPageViewModel;
import com.app.aisheildme.bytrix.feature.profile.StaticPageViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.profile.StaticPageViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.profile.StaticPageViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.retail.RetailViewModel;
import com.app.aisheildme.bytrix.feature.retail.RetailViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.retail.RetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.retail.RetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.share.ShareViewModel;
import com.app.aisheildme.bytrix.feature.share.ShareViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.share.ShareViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.share.ShareViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.feature.vault.VaultGateViewModel;
import com.app.aisheildme.bytrix.feature.vault.VaultGateViewModel_HiltModules;
import com.app.aisheildme.bytrix.feature.vault.VaultGateViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.feature.vault.VaultGateViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.app.aisheildme.bytrix.navigation.AppViewModel;
import com.app.aisheildme.bytrix.navigation.AppViewModel_HiltModules;
import com.app.aisheildme.bytrix.navigation.AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.app.aisheildme.bytrix.navigation.AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerAiShieldMeApp_HiltComponents_SingletonC {
  private DaggerAiShieldMeApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public AiShieldMeApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements AiShieldMeApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements AiShieldMeApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements AiShieldMeApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements AiShieldMeApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements AiShieldMeApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements AiShieldMeApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements AiShieldMeApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public AiShieldMeApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends AiShieldMeApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends AiShieldMeApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends AiShieldMeApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends AiShieldMeApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(28).put(AddInvoiceViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AddInvoiceViewModel_HiltModules.KeyModule.provide()).put(AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AppViewModel_HiltModules.KeyModule.provide()).put(AvailabilityViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AvailabilityViewModel_HiltModules.KeyModule.provide()).put(BankingViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, BankingViewModel_HiltModules.KeyModule.provide()).put(BasicInfoViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, BasicInfoViewModel_HiltModules.KeyModule.provide()).put(CheckInFormViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CheckInFormViewModel_HiltModules.KeyModule.provide()).put(CheckInHistoryViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CheckInHistoryViewModel_HiltModules.KeyModule.provide()).put(CheckInPreviewViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CheckInPreviewViewModel_HiltModules.KeyModule.provide()).put(CheckInReportViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CheckInReportViewModel_HiltModules.KeyModule.provide()).put(CreateVaultPinViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CreateVaultPinViewModel_HiltModules.KeyModule.provide()).put(DocumentsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DocumentsViewModel_HiltModules.KeyModule.provide()).put(EditProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, EditProfileViewModel_HiltModules.KeyModule.provide()).put(FoldersViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, FoldersViewModel_HiltModules.KeyModule.provide()).put(HealthcareViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HealthcareViewModel_HiltModules.KeyModule.provide()).put(HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HomeViewModel_HiltModules.KeyModule.provide()).put(LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LoginViewModel_HiltModules.KeyModule.provide()).put(NotificationViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, NotificationViewModel_HiltModules.KeyModule.provide()).put(ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProfileViewModel_HiltModules.KeyModule.provide()).put(ResetPasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ResetPasswordViewModel_HiltModules.KeyModule.provide()).put(ResetPinViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ResetPinViewModel_HiltModules.KeyModule.provide()).put(RetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, RetailViewModel_HiltModules.KeyModule.provide()).put(SetupProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SetupProfileViewModel_HiltModules.KeyModule.provide()).put(ShareViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ShareViewModel_HiltModules.KeyModule.provide()).put(SignupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SignupViewModel_HiltModules.KeyModule.provide()).put(SplashViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SplashViewModel_HiltModules.KeyModule.provide()).put(StaticPageViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, StaticPageViewModel_HiltModules.KeyModule.provide()).put(VaultGateViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, VaultGateViewModel_HiltModules.KeyModule.provide()).put(VerifyEmailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, VerifyEmailViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectDetailHandoff(instance, singletonCImpl.detailHandoffProvider.get());
      MainActivity_MembersInjector.injectFileOpener(instance, singletonCImpl.fileOpenerProvider.get());
      return instance;
    }
  }

  private static final class ViewModelCImpl extends AiShieldMeApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<AddInvoiceViewModel> addInvoiceViewModelProvider;

    Provider<AppViewModel> appViewModelProvider;

    Provider<AvailabilityViewModel> availabilityViewModelProvider;

    Provider<BankingViewModel> bankingViewModelProvider;

    Provider<BasicInfoViewModel> basicInfoViewModelProvider;

    Provider<CheckInFormViewModel> checkInFormViewModelProvider;

    Provider<CheckInHistoryViewModel> checkInHistoryViewModelProvider;

    Provider<CheckInPreviewViewModel> checkInPreviewViewModelProvider;

    Provider<CheckInReportViewModel> checkInReportViewModelProvider;

    Provider<CreateVaultPinViewModel> createVaultPinViewModelProvider;

    Provider<DocumentsViewModel> documentsViewModelProvider;

    Provider<EditProfileViewModel> editProfileViewModelProvider;

    Provider<FoldersViewModel> foldersViewModelProvider;

    Provider<HealthcareViewModel> healthcareViewModelProvider;

    Provider<HomeViewModel> homeViewModelProvider;

    Provider<LoginViewModel> loginViewModelProvider;

    Provider<NotificationViewModel> notificationViewModelProvider;

    Provider<ProfileViewModel> profileViewModelProvider;

    Provider<ResetPasswordViewModel> resetPasswordViewModelProvider;

    Provider<ResetPinViewModel> resetPinViewModelProvider;

    Provider<RetailViewModel> retailViewModelProvider;

    Provider<SetupProfileViewModel> setupProfileViewModelProvider;

    Provider<ShareViewModel> shareViewModelProvider;

    Provider<SignupViewModel> signupViewModelProvider;

    Provider<SplashViewModel> splashViewModelProvider;

    Provider<StaticPageViewModel> staticPageViewModelProvider;

    Provider<VaultGateViewModel> vaultGateViewModelProvider;

    Provider<VerifyEmailViewModel> verifyEmailViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);
      initialize2(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addInvoiceViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.appViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.availabilityViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.bankingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.basicInfoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.checkInFormViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.checkInHistoryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.checkInPreviewViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.checkInReportViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.createVaultPinViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.documentsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.editProfileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.foldersViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.healthcareViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15);
      this.notificationViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 17);
      this.resetPasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 18);
      this.resetPinViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 19);
      this.retailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 20);
      this.setupProfileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 21);
      this.shareViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 22);
      this.signupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 23);
      this.splashViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 24);
    }

    @SuppressWarnings("unchecked")
    private void initialize2(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.staticPageViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 25);
      this.vaultGateViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 26);
      this.verifyEmailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 27);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(28).put(AddInvoiceViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (addInvoiceViewModelProvider))).put(AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (appViewModelProvider))).put(AvailabilityViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (availabilityViewModelProvider))).put(BankingViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (bankingViewModelProvider))).put(BasicInfoViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (basicInfoViewModelProvider))).put(CheckInFormViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (checkInFormViewModelProvider))).put(CheckInHistoryViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (checkInHistoryViewModelProvider))).put(CheckInPreviewViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (checkInPreviewViewModelProvider))).put(CheckInReportViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (checkInReportViewModelProvider))).put(CreateVaultPinViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (createVaultPinViewModelProvider))).put(DocumentsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (documentsViewModelProvider))).put(EditProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (editProfileViewModelProvider))).put(FoldersViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (foldersViewModelProvider))).put(HealthcareViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (healthcareViewModelProvider))).put(HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (homeViewModelProvider))).put(LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (loginViewModelProvider))).put(NotificationViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (notificationViewModelProvider))).put(ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (profileViewModelProvider))).put(ResetPasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (resetPasswordViewModelProvider))).put(ResetPinViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (resetPinViewModelProvider))).put(RetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (retailViewModelProvider))).put(SetupProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (setupProfileViewModelProvider))).put(ShareViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (shareViewModelProvider))).put(SignupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (signupViewModelProvider))).put(SplashViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (splashViewModelProvider))).put(StaticPageViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (staticPageViewModelProvider))).put(VaultGateViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (vaultGateViewModelProvider))).put(VerifyEmailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (verifyEmailViewModelProvider))).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.app.aisheildme.bytrix.feature.bookkeeper.AddInvoiceViewModel
          return (T) new AddInvoiceViewModel(singletonCImpl.contentRepositoryProvider.get());

          case 1: // com.app.aisheildme.bytrix.navigation.AppViewModel
          return (T) new AppViewModel(singletonCImpl.authRepositoryProvider.get());

          case 2: // com.app.aisheildme.bytrix.feature.profile.AvailabilityViewModel
          return (T) new AvailabilityViewModel(singletonCImpl.contentRepositoryProvider.get());

          case 3: // com.app.aisheildme.bytrix.feature.banking.BankingViewModel
          return (T) new BankingViewModel(singletonCImpl.recordRepositoryProvider.get());

          case 4: // com.app.aisheildme.bytrix.feature.auth.basicinfo.BasicInfoViewModel
          return (T) new BasicInfoViewModel(singletonCImpl.authRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 5: // com.app.aisheildme.bytrix.feature.checkin.CheckInFormViewModel
          return (T) new CheckInFormViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.patientProfileRepositoryProvider.get(), singletonCImpl.checkInAiRepositoryProvider.get(), singletonCImpl.checkInHistoryRepositoryProvider.get(), singletonCImpl.documentTextReaderProvider.get(), singletonCImpl.detailHandoffProvider.get());

          case 6: // com.app.aisheildme.bytrix.feature.checkinhistory.CheckInHistoryViewModel
          return (T) new CheckInHistoryViewModel(singletonCImpl.checkInHistoryRepositoryProvider.get());

          case 7: // com.app.aisheildme.bytrix.feature.checkin.CheckInPreviewViewModel
          return (T) new CheckInPreviewViewModel(singletonCImpl.patientProfileRepositoryProvider.get(), singletonCImpl.detailHandoffProvider.get());

          case 8: // com.app.aisheildme.bytrix.feature.checkin.CheckInReportViewModel
          return (T) new CheckInReportViewModel(singletonCImpl.patientReviewPdfGeneratorProvider.get(), singletonCImpl.localNotifierProvider.get(), singletonCImpl.detailHandoffProvider.get());

          case 9: // com.app.aisheildme.bytrix.feature.auth.vaultpin.CreateVaultPinViewModel
          return (T) new CreateVaultPinViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.biometricAvailabilityProvider.get());

          case 10: // com.app.aisheildme.bytrix.feature.documents.DocumentsViewModel
          return (T) new DocumentsViewModel(singletonCImpl.documentRepositoryProvider.get(), singletonCImpl.authRepositoryProvider.get(), singletonCImpl.fileOpenerProvider.get(), viewModelCImpl.savedStateHandle);

          case 11: // com.app.aisheildme.bytrix.feature.profile.EditProfileViewModel
          return (T) new EditProfileViewModel(singletonCImpl.authRepositoryProvider.get());

          case 12: // com.app.aisheildme.bytrix.feature.folders.FoldersViewModel
          return (T) new FoldersViewModel(singletonCImpl.documentRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 13: // com.app.aisheildme.bytrix.feature.healthcare.HealthcareViewModel
          return (T) new HealthcareViewModel(singletonCImpl.recordRepositoryProvider.get(), singletonCImpl.checkInHistoryRepositoryProvider.get());

          case 14: // com.app.aisheildme.bytrix.feature.home.HomeViewModel
          return (T) new HomeViewModel(singletonCImpl.documentRepositoryProvider.get(), singletonCImpl.recordRepositoryProvider.get(), singletonCImpl.authRepositoryProvider.get());

          case 15: // com.app.aisheildme.bytrix.feature.auth.login.LoginViewModel
          return (T) new LoginViewModel(singletonCImpl.authRepositoryProvider.get());

          case 16: // com.app.aisheildme.bytrix.feature.notification.NotificationViewModel
          return (T) new NotificationViewModel(singletonCImpl.notificationRepositoryProvider.get());

          case 17: // com.app.aisheildme.bytrix.feature.profile.ProfileViewModel
          return (T) new ProfileViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.biometricAvailabilityProvider.get());

          case 18: // com.app.aisheildme.bytrix.feature.auth.resetpassword.ResetPasswordViewModel
          return (T) new ResetPasswordViewModel(singletonCImpl.authRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 19: // com.app.aisheildme.bytrix.feature.profile.ResetPinViewModel
          return (T) new ResetPinViewModel(singletonCImpl.authRepositoryProvider.get());

          case 20: // com.app.aisheildme.bytrix.feature.retail.RetailViewModel
          return (T) new RetailViewModel(singletonCImpl.recordRepositoryProvider.get());

          case 21: // com.app.aisheildme.bytrix.feature.auth.setupprofile.SetupProfileViewModel
          return (T) new SetupProfileViewModel(singletonCImpl.authRepositoryProvider.get());

          case 22: // com.app.aisheildme.bytrix.feature.share.ShareViewModel
          return (T) new ShareViewModel(singletonCImpl.documentRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 23: // com.app.aisheildme.bytrix.feature.auth.signup.SignupViewModel
          return (T) new SignupViewModel();

          case 24: // com.app.aisheildme.bytrix.feature.auth.splash.SplashViewModel
          return (T) new SplashViewModel(singletonCImpl.authRepositoryProvider.get());

          case 25: // com.app.aisheildme.bytrix.feature.profile.StaticPageViewModel
          return (T) new StaticPageViewModel(singletonCImpl.contentRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 26: // com.app.aisheildme.bytrix.feature.vault.VaultGateViewModel
          return (T) new VaultGateViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.biometricAvailabilityProvider.get());

          case 27: // com.app.aisheildme.bytrix.feature.auth.verifyemail.VerifyEmailViewModel
          return (T) new VerifyEmailViewModel(singletonCImpl.authRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends AiShieldMeApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends AiShieldMeApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends AiShieldMeApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<DetailHandoff> detailHandoffProvider;

    Provider<Json> provideJsonProvider;

    Provider<SessionStore> provideSessionStoreProvider;

    Provider<OkHttpClient> provideOkHttpProvider;

    Provider<FileOpener> fileOpenerProvider;

    Provider<Retrofit> provideRetrofitProvider;

    Provider<PagesApi> providePagesApiProvider;

    Provider<MiscApi> provideMiscApiProvider;

    Provider<ContentRepository> contentRepositoryProvider;

    Provider<AuthApi> provideAuthApiProvider;

    Provider<VaultLock> vaultLockProvider;

    Provider<FirebaseDeviceTokenProvider> firebaseDeviceTokenProvider;

    Provider<DocumentApi> provideDocumentApiProvider;

    Provider<AuthRepository> authRepositoryProvider;

    Provider<RecordApi> provideRecordApiProvider;

    Provider<RecordRepository> recordRepositoryProvider;

    Provider<PatientProfileApi> providePatientProfileApiProvider;

    Provider<PatientProfileRepository> patientProfileRepositoryProvider;

    Provider<OnDeviceAi> onDeviceAiProvider;

    Provider<CheckInAiRepository> checkInAiRepositoryProvider;

    Provider<AppDatabase> provideDatabaseProvider;

    Provider<CheckInHistoryDao> provideCheckInHistoryDaoProvider;

    Provider<CheckInHistoryRepository> checkInHistoryRepositoryProvider;

    Provider<DocumentTextReader> documentTextReaderProvider;

    Provider<PatientReviewPdfGenerator> patientReviewPdfGeneratorProvider;

    Provider<LocalNotifier> localNotifierProvider;

    Provider<BiometricAvailability> biometricAvailabilityProvider;

    Provider<DocumentRepository> documentRepositoryProvider;

    Provider<NotificationApi> provideNotificationApiProvider;

    Provider<NotificationRepository> notificationRepositoryProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);
      initialize2(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.detailHandoffProvider = DoubleCheck.provider(new SwitchingProvider<DetailHandoff>(singletonCImpl, 0));
      this.provideJsonProvider = DoubleCheck.provider(new SwitchingProvider<Json>(singletonCImpl, 4));
      this.provideSessionStoreProvider = DoubleCheck.provider(new SwitchingProvider<SessionStore>(singletonCImpl, 3));
      this.provideOkHttpProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 2));
      this.fileOpenerProvider = DoubleCheck.provider(new SwitchingProvider<FileOpener>(singletonCImpl, 1));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 7));
      this.providePagesApiProvider = DoubleCheck.provider(new SwitchingProvider<PagesApi>(singletonCImpl, 6));
      this.provideMiscApiProvider = DoubleCheck.provider(new SwitchingProvider<MiscApi>(singletonCImpl, 8));
      this.contentRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ContentRepository>(singletonCImpl, 5));
      this.provideAuthApiProvider = DoubleCheck.provider(new SwitchingProvider<AuthApi>(singletonCImpl, 10));
      this.vaultLockProvider = DoubleCheck.provider(new SwitchingProvider<VaultLock>(singletonCImpl, 11));
      this.firebaseDeviceTokenProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseDeviceTokenProvider>(singletonCImpl, 12));
      this.provideDocumentApiProvider = DoubleCheck.provider(new SwitchingProvider<DocumentApi>(singletonCImpl, 13));
      this.authRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, 9));
      this.provideRecordApiProvider = DoubleCheck.provider(new SwitchingProvider<RecordApi>(singletonCImpl, 15));
      this.recordRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<RecordRepository>(singletonCImpl, 14));
      this.providePatientProfileApiProvider = DoubleCheck.provider(new SwitchingProvider<PatientProfileApi>(singletonCImpl, 17));
      this.patientProfileRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PatientProfileRepository>(singletonCImpl, 16));
      this.onDeviceAiProvider = DoubleCheck.provider(new SwitchingProvider<OnDeviceAi>(singletonCImpl, 19));
      this.checkInAiRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CheckInAiRepository>(singletonCImpl, 18));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 22));
      this.provideCheckInHistoryDaoProvider = DoubleCheck.provider(new SwitchingProvider<CheckInHistoryDao>(singletonCImpl, 21));
      this.checkInHistoryRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CheckInHistoryRepository>(singletonCImpl, 20));
      this.documentTextReaderProvider = DoubleCheck.provider(new SwitchingProvider<DocumentTextReader>(singletonCImpl, 23));
      this.patientReviewPdfGeneratorProvider = DoubleCheck.provider(new SwitchingProvider<PatientReviewPdfGenerator>(singletonCImpl, 24));
    }

    @SuppressWarnings("unchecked")
    private void initialize2(final ApplicationContextModule applicationContextModuleParam) {
      this.localNotifierProvider = DoubleCheck.provider(new SwitchingProvider<LocalNotifier>(singletonCImpl, 25));
      this.biometricAvailabilityProvider = DoubleCheck.provider(new SwitchingProvider<BiometricAvailability>(singletonCImpl, 26));
      this.documentRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<DocumentRepository>(singletonCImpl, 27));
      this.provideNotificationApiProvider = DoubleCheck.provider(new SwitchingProvider<NotificationApi>(singletonCImpl, 29));
      this.notificationRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<NotificationRepository>(singletonCImpl, 28));
    }

    @Override
    public void injectAiShieldMeApp(AiShieldMeApp aiShieldMeApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.app.aisheildme.bytrix.core.common.DetailHandoff
          return (T) new DetailHandoff();

          case 1: // com.app.aisheildme.bytrix.core.util.FileOpener
          return (T) new FileOpener(singletonCImpl.provideOkHttpProvider.get());

          case 2: // okhttp3.OkHttpClient
          return (T) NetworkModule_ProvideOkHttpFactory.provideOkHttp(singletonCImpl.provideSessionStoreProvider.get(), singletonCImpl.provideJsonProvider.get());

          case 3: // com.app.aisheildme.bytrix.data.local.SessionStore
          return (T) NetworkModule_ProvideSessionStoreFactory.provideSessionStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideJsonProvider.get());

          case 4: // kotlinx.serialization.json.Json
          return (T) NetworkModule_ProvideJsonFactory.provideJson();

          case 5: // com.app.aisheildme.bytrix.data.repository.ContentRepository
          return (T) new ContentRepository(singletonCImpl.providePagesApiProvider.get(), singletonCImpl.provideMiscApiProvider.get());

          case 6: // com.app.aisheildme.bytrix.data.remote.api.PagesApi
          return (T) DataModule_ProvidePagesApiFactory.providePagesApi(singletonCImpl.provideRetrofitProvider.get());

          case 7: // retrofit2.Retrofit
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpProvider.get(), singletonCImpl.provideJsonProvider.get());

          case 8: // com.app.aisheildme.bytrix.data.remote.api.MiscApi
          return (T) DataModule_ProvideMiscApiFactory.provideMiscApi(singletonCImpl.provideRetrofitProvider.get());

          case 9: // com.app.aisheildme.bytrix.data.repository.AuthRepository
          return (T) new AuthRepository(singletonCImpl.provideAuthApiProvider.get(), singletonCImpl.provideSessionStoreProvider.get(), singletonCImpl.vaultLockProvider.get(), singletonCImpl.firebaseDeviceTokenProvider.get(), singletonCImpl.provideDocumentApiProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 10: // com.app.aisheildme.bytrix.data.remote.api.AuthApi
          return (T) NetworkModule_ProvideAuthApiFactory.provideAuthApi(singletonCImpl.provideRetrofitProvider.get());

          case 11: // com.app.aisheildme.bytrix.data.local.VaultLock
          return (T) new VaultLock();

          case 12: // com.app.aisheildme.bytrix.data.FirebaseDeviceTokenProvider
          return (T) new FirebaseDeviceTokenProvider();

          case 13: // com.app.aisheildme.bytrix.data.remote.api.DocumentApi
          return (T) DataModule_ProvideDocumentApiFactory.provideDocumentApi(singletonCImpl.provideRetrofitProvider.get());

          case 14: // com.app.aisheildme.bytrix.data.repository.RecordRepository
          return (T) new RecordRepository(singletonCImpl.provideRecordApiProvider.get());

          case 15: // com.app.aisheildme.bytrix.data.remote.api.RecordApi
          return (T) DataModule_ProvideRecordApiFactory.provideRecordApi(singletonCImpl.provideRetrofitProvider.get());

          case 16: // com.app.aisheildme.bytrix.data.repository.PatientProfileRepository
          return (T) new PatientProfileRepository(singletonCImpl.providePatientProfileApiProvider.get(), singletonCImpl.provideJsonProvider.get());

          case 17: // com.app.aisheildme.bytrix.data.remote.api.PatientProfileApi
          return (T) DataModule_ProvidePatientProfileApiFactory.providePatientProfileApi(singletonCImpl.provideRetrofitProvider.get());

          case 18: // com.app.aisheildme.bytrix.data.repository.CheckInAiRepository
          return (T) new CheckInAiRepository(singletonCImpl.onDeviceAiProvider.get());

          case 19: // com.app.aisheildme.bytrix.data.ai.OnDeviceAi
          return (T) new OnDeviceAi();

          case 20: // com.app.aisheildme.bytrix.data.repository.CheckInHistoryRepository
          return (T) new CheckInHistoryRepository(singletonCImpl.provideCheckInHistoryDaoProvider.get());

          case 21: // com.app.aisheildme.bytrix.data.local.db.CheckInHistoryDao
          return (T) DataModule_ProvideCheckInHistoryDaoFactory.provideCheckInHistoryDao(singletonCImpl.provideDatabaseProvider.get());

          case 22: // com.app.aisheildme.bytrix.data.local.db.AppDatabase
          return (T) DataModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 23: // com.app.aisheildme.bytrix.data.ai.DocumentTextReader
          return (T) new DocumentTextReader(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 24: // com.app.aisheildme.bytrix.data.pdf.PatientReviewPdfGenerator
          return (T) new PatientReviewPdfGenerator(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 25: // com.app.aisheildme.bytrix.core.util.LocalNotifier
          return (T) new LocalNotifier(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 26: // com.app.aisheildme.bytrix.core.util.BiometricAvailability
          return (T) new BiometricAvailability(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 27: // com.app.aisheildme.bytrix.data.repository.DocumentRepository
          return (T) new DocumentRepository(singletonCImpl.provideDocumentApiProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 28: // com.app.aisheildme.bytrix.data.repository.NotificationRepository
          return (T) new NotificationRepository(singletonCImpl.provideNotificationApiProvider.get());

          case 29: // com.app.aisheildme.bytrix.data.remote.api.NotificationApi
          return (T) DataModule_ProvideNotificationApiFactory.provideNotificationApi(singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
