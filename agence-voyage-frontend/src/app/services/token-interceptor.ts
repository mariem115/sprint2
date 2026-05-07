import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const url = req.url;
  if (
    url.includes('/login') ||
    url.includes('/api/register') ||
    url.includes('/verifyEmail')
  ) {
    return next(req);
  }

  const authService = inject(AuthService);
  const jwt = authService.getToken();
  if (!jwt) {
    return next(req);
  }

  const reqWithToken = req.clone({
    setHeaders: { Authorization: 'Bearer ' + jwt },
  });
  return next(reqWithToken);
};
