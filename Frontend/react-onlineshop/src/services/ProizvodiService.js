import axios from 'axios';
import authHeader from './auth-header';

const PROIZVOD_API_BASE_URL = "http://localhost:8080/api/proizvodi";

class ProizvodiService { 

    getKategorije(){
        return axios.get(PROIZVOD_API_BASE_URL + '/kategorije');
    }

    getProizvodi(config){
        return axios.get(PROIZVOD_API_BASE_URL, config);
    }

    createProizvod(proizvod){
        return axios.post(PROIZVOD_API_BASE_URL, proizvod, { headers: authHeader() });
    }

    getProizvodById(proizvodId){
        return axios.get(PROIZVOD_API_BASE_URL + '/' + proizvodId, { headers: authHeader() });
    }

    updateProizvod(proizvod, proizvodId){
        return axios.put(PROIZVOD_API_BASE_URL + '/' + proizvodId, proizvod, { headers: authHeader() });
    }

    deleteProizvod(proizvodId){
        return axios.delete(PROIZVOD_API_BASE_URL + '/' + proizvodId, { headers: authHeader() });
    }


}

export default new ProizvodiService()