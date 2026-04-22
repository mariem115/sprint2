import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private usersBaseUrl = 'http://localhost:8081/users';
  private loginUrl = `${this.usersBaseUrl}/login`;

  private readonly TOKEN_KEY = 'jwt-token';
  private readonly USERNAME_KEY = 'username';

  login(username: string, password: string): Observable<HttpResponse<unknown>> {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post(this.loginUrl, body.toString(), {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      observe: 'response',
      responseType: 'text',
    });
  }

  saveToken(jwt: string): void {
    localStorage.setItem(this.TOKEN_KEY, jwt);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  saveUsername(username: string): void {
    localStorage.setItem(this.USERNAME_KEY, username);
  }

  getUsername(): string | null {
    return localStorage.getItem(this.USERNAME_KEY);
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USERNAME_KEY);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  decodeJWT(): { sub?: string; roles?: string[]; exp?: number } | null {
    const token = this.getToken();
    if (!token) {
      return null;
    }
    try {
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload));
    } catch {
      return null;
    }
  }

  hasRole(role: string): boolean {
    const claims = this.decodeJWT();
    return claims?.roles?.includes(role) ?? false;
  }

  register(user: User): Observable<unknown> {
    return this.http.post(`${this.usersBaseUrl}/api/register`, user, {
      headers: { 'Content-Type': 'application/json' },
    });
  }

  verifyEmail(username: string, code: string): Observable<string> {
    const params = `username=${encodeURIComponent(username)}&code=${encodeURIComponent(code)}`;
    return this.http.get(`${this.usersBaseUrl}/api/verifyEmail?${params}`, {
      responseType: 'text',
    });
  }
}
