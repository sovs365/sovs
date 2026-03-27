package com.university.voting.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0018H\u0002J\u0016\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/university/voting/api/RetrofitClient;", "", "()V", "DEFAULT_BASE_URL", "", "_apiService", "Lcom/university/voting/api/ApiService;", "_baseUrl", "apiService", "getApiService", "()Lcom/university/voting/api/ApiService;", "baseUrl", "getBaseUrl", "()Ljava/lang/String;", "httpClient", "error/NonExistentClass", "Lerror/NonExistentClass;", "loggingInterceptor", "retrofit", "Lretrofit2/Retrofit;", "buildRetrofit", "getAuthHeader", "token", "init", "", "context", "Landroid/content/Context;", "rebuildRetrofit", "setServerUrl", "url", "app_debug"})
public final class RetrofitClient {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DEFAULT_BASE_URL = "https://sovs-backend.onrender.com/";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String _baseUrl = "https://sovs-backend.onrender.com/";
    @org.jetbrains.annotations.NotNull()
    private static final error.NonExistentClass loggingInterceptor = null;
    @org.jetbrains.annotations.NotNull()
    private static final error.NonExistentClass httpClient = null;
    @org.jetbrains.annotations.NotNull()
    private static retrofit2.Retrofit retrofit;
    @org.jetbrains.annotations.NotNull()
    private static com.university.voting.api.ApiService _apiService;
    @org.jetbrains.annotations.NotNull()
    public static final com.university.voting.api.RetrofitClient INSTANCE = null;
    
    private RetrofitClient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBaseUrl() {
        return null;
    }
    
    /**
     * Initializes Retrofit for the fixed cloud backend.
     */
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Kept for compatibility. The app uses only the fixed cloud backend URL.
     */
    public final void setServerUrl(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    private final retrofit2.Retrofit buildRetrofit() {
        return null;
    }
    
    private final void rebuildRetrofit() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.university.voting.api.ApiService getApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return null;
    }
}