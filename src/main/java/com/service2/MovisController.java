package com.service2;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.jdbc.core.JdbcTemplate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apiMovi")
public class MovisController {
	
	public interface RestServiceExecution {

		public Object execute() throws Exception;
		
	}
	public Map<String, Object> executeService(RestServiceExecution e) {
		Map<String, Object> res = new HashMap<>();
		try {
			Object r = e.execute();
			res.put("return", r);
		} catch (Exception e2) {
			
			res.put("result", "Error");
			res.put("error", e2.getMessage());
		}
		return res;
	}
	
	
	@Autowired
	private MovisService movisService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/allMovi")
    public List<MovisModel> list(){
        return movisService.listAll();
    }
    
    @PostMapping("/add")
    public String add(@RequestBody MovisModel movismodel) {
    	movisService.alta(movismodel);
    	return "Movimiento agregado";
    }
 

	public List getcompAll(String idcli) {
		String sql = "select * from Tyg.movis_bank where idcli= " + idcli + " order by fecha DESC";
	
		return jdbcTemplate.queryForList(sql);
}
	
	@GetMapping("/moviid/{idcli}")
	public Map<String, Object> getcompAll1(@PathVariable (value="idcli") String idcli) {
		return executeService(() -> {
			return getcompAll(idcli);			
		});
	}
    
	// llamado a servicio externo
	@Value("${rutaserviceclient}")
	private String rutaserviceclient1;
	@GetMapping("/datoscliente/{idcli}")
	 public String Procesacli (@PathVariable (value="idcli") String idcli) throws Exception {
		try {  
			ObjectMapper mappercoun = null;
			mappercoun = new ObjectMapper();
			//CountryService conser = new CountryService();
			//String prefijoPais = ippais.getCountryCode3();
			String Url2 = rutaserviceclient1 + "/apiCli/" + idcli;
			String res2 = Request.Get(Url2)
						.execute()
						.returnContent()
						.asString();
			//List<Map<String, Object>> lstCountry = mappercoun.readValue(res2, new TypeReference<List<Map<String, Object>>>(){});
			//Map<String, Object> lstCountry = mappercoun.readValue(res2, new TypeReference<Map<String, Object>>(){});
			//return lstCountry.toString();
			return res2.toString();
		}
		catch (IOException e ) { }
			return "error en la operacion";
	}
}
