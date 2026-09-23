package com.app.aisheildme.bytrix;

import com.app.aisheildme.bytrix.core.common.DetailHandoff;
import com.app.aisheildme.bytrix.core.util.FileOpener;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<DetailHandoff> detailHandoffProvider;

  private final Provider<FileOpener> fileOpenerProvider;

  public MainActivity_MembersInjector(Provider<DetailHandoff> detailHandoffProvider,
      Provider<FileOpener> fileOpenerProvider) {
    this.detailHandoffProvider = detailHandoffProvider;
    this.fileOpenerProvider = fileOpenerProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<DetailHandoff> detailHandoffProvider,
      Provider<FileOpener> fileOpenerProvider) {
    return new MainActivity_MembersInjector(detailHandoffProvider, fileOpenerProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectDetailHandoff(instance, detailHandoffProvider.get());
    injectFileOpener(instance, fileOpenerProvider.get());
  }

  @InjectedFieldSignature("com.app.aisheildme.bytrix.MainActivity.detailHandoff")
  public static void injectDetailHandoff(MainActivity instance, DetailHandoff detailHandoff) {
    instance.detailHandoff = detailHandoff;
  }

  @InjectedFieldSignature("com.app.aisheildme.bytrix.MainActivity.fileOpener")
  public static void injectFileOpener(MainActivity instance, FileOpener fileOpener) {
    instance.fileOpener = fileOpener;
  }
}
