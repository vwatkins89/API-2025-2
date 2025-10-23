package br.com.serratec.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler; // Import necessário
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
   
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<String> erros = new ArrayList<>();
		
		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
			erros.add(erro.getField() + " - " + erro.getDefaultMessage());
		}
		
		ErroResposta erroResposta = new ErroResposta(status.value(),"Existem campos inválidos",
				LocalDateTime.now(), erros);
		
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
	
	
    // Retorna 400 Bad Request (Padrão para erros de negócio/dados inválidos)
    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ErroResposta> handleUsuarioException(UsuarioException ex) {
        
        ErroResposta erroResposta = new ErroResposta(
            HttpStatus.BAD_REQUEST.value(), // Código 400
            "Regra de Negócio Violada",
            LocalDateTime.now(), 
            List.of(ex.getMessage()) // Mensagem do Service (ex: "Vendedor não encontrado")
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }
    
    // 3. TRATAMENTO DE 404 NOT FOUND GENÉRICO (Requisito: Tratar recursos não encontrados)
    // Intercepta 404s que podem ser lançados pelo Spring internamente
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResposta> handleResponseStatusException(ResponseStatusException ex) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
             ErroResposta erroResposta = new ErroResposta(
                HttpStatus.NOT_FOUND.value(), // Código 404
                "Recurso não encontrado",
                LocalDateTime.now(), 
                List.of(ex.getReason() != null ? ex.getReason() : "O recurso solicitado não existe.")
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
        }
        // Para outros status, deixa o tratamento padrão
        return ResponseEntity.status(ex.getStatusCode()).build();
    }
}