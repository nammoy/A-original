package com.app.aisheildme.bytrix.data.repository;

import android.content.Context;
import com.app.aisheildme.bytrix.data.local.SessionStore;
import com.app.aisheildme.bytrix.data.local.VaultLock;
import com.app.aisheildme.bytrix.data.remote.api.AuthApi;
import com.app.aisheildme.bytrix.data.remote.api.DocumentApi;
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
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<AuthApi> apiProvider;

  private final Provider<SessionStore> sessionStoreProvider;

  private final Provider<VaultLock> vaultLockProvider;

  private final Provider<DeviceTokenProvider> deviceTokenProvider;

  private final Provider<DocumentApi> documentApiProvider;

  private final Provider<Context> contextProvider;

  public AuthRepository_Factory(Provider<AuthApi> apiProvider,
      Provider<SessionStore> sessionStoreProvider, Provider<VaultLock> vaultLockProvider,
      Provider<DeviceTokenProvider> deviceTokenProvider, Provider<DocumentApi> documentApiProvider,
      Provider<Context> contextProvider) {
    this.apiProvider = apiProvider;
    this.sessionStoreProvider = sessionStoreProvider;
    this.vaultLockProvider = vaultLockProvider;
    this.deviceTokenProvider = deviceTokenProvider;
    this.documentApiProvider = documentApiProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(apiProvider.get(), sessionStoreProvider.get(), vaultLockProvider.get(), deviceTokenProvider.get(), documentApiProvider.get(), contextProvider.get());
  }

  public static AuthRepository_Factory create(Provider<AuthApi> apiProvider,
      Provider<SessionStore> sessionStoreProvider, Provider<VaultLock> vaultLockProvider,
      Provider<DeviceTokenProvider> deviceTokenProvider, Provider<DocumentApi> documentApiProvider,
      Provider<Context> contextProvider) {
    return new AuthRepository_Factory(apiProvider, sessionStoreProvider, vaultLockProvider, deviceTokenProvider, documentApiProvider, contextProvider);
  }

  public static AuthRepository newInstance(AuthApi api, SessionStore sessionStore,
      VaultLock vaultLock, DeviceTokenProvider deviceTokenProvider, DocumentApi documentApi,
      Context context) {
    return new AuthRepository(api, sessionStore, vaultLock, deviceTokenProvider, documentApi, context);
  }
}
