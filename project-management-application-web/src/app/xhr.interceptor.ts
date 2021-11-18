import {Injectable} from "@angular/core";
import {HttpHandler, HttpRequest} from "@angular/common/http";

@Injectable()
export class XhrInterceptor
{
  intercept(req: HttpRequest<any>, next: HttpHandler)
  {
    const xhr = req.clone(
      {
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}
