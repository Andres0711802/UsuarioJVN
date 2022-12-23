import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Usuario } from '../model/usuario.model';
import { pageable}  from '../model/pageable.model';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environment';


@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService {
    
    API_URI = environment.API_URI;
    SIZE_PAGEABLE = environment.size;
  
    constructor(private http: HttpClient) { }
    
    getUsuarios(req:pageable):Observable<any[]> {
      let params = new HttpParams();
      params=params.append('page',String(req.number));
      params=params.append('size',String(this.SIZE_PAGEABLE));
      
      const httpOptions={
        Headers:new HttpHeaders({
          'Accept':'*/*',
          'Content-Type':'application/json'
        }),
        params:params
      }

      console.log(this.API_URI + '/app/usuarios/');
  
      return this.http.get<any[]>(this.API_URI + '/app/usuarios/',httpOptions)
      
    }
    
    getUsuarioId(id:any):Observable<Usuario>{
      return this.http.get<Usuario>(this.API_URI + '/app/usuarios/'+id)
      .pipe(catchError(e=>{
        return throwError(e);
      }))    
    }

    eliminar(id:any):Observable<Usuario>{
      return this.http.delete<Usuario>(this.API_URI + '/app/usuarios/'+id)
      .pipe(catchError(e=>{
        return throwError(e);
      }))    
    }

    getRegistrar(usuario: Usuario):Observable<Usuario>{
      return this.http.post<Usuario>(this.API_URI + '/app/usuarios/', usuario)
      .pipe(catchError(e=>{
        return throwError(e);
      }))    
    }

    editar(id: number,usuario: Usuario):Observable<Usuario>{
      return this.http.put<Usuario>(this.API_URI + '/app/usuarios/'+id, usuario)
      .pipe(catchError(e=>{
        return throwError(e);
      }))    
    }
  
  }
  