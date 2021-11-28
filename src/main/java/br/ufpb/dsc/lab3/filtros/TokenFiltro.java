package br.ufpb.dsc.lab3.filtros;

import br.ufpb.dsc.lab3.servicos.JWTServico;
import io.jsonwebtoken.*;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFiltro extends GenericFilterBean {

    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token inexistente ou mal formatado!");
            return;

        }
        String token = header.substring(TOKEN_INDEX);
        try {
            Jwts.parser().setSigningKey(JWTServico.TOKEN_KEY).parseClaimsJws(token).getBody();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
                | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }
}