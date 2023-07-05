/* COSTANTI */
const PIZZE_API_URL = "http://localhost:8080/api/v1/pizze";
const contentDOM = document.getElementById("content");

/* TEST IVOCAZIONE */
console.log(axios);

/* API */
const getPizze = async () => {
  try {
    const response = await axios.get(PIZZE_API_URL);
    console.log(response);
    return response.data; // Restituisci la risposta API
  } catch (error) {
    console.log(error);
  }
};

/* DOM MANIPULATION */
const createPizzaList = (data) => {
  if (data.length > 0) {
    let list = "<ul>";
    //itero sulla lista pizze
    data.forEach((element) => {
      list += `<li>${element.name}</li>`;
    });
    list += "</ul>";
    return list;
  } else {
    return '<div class="alert alert-info">La Lista Pizze è vuota</div>';
  }
};

const loadData = async () => {
  // prendo i dati API
  const data = await getPizze();
  // li appendo al dom
  contentDOM.innerHTML = createPizzaList(data);
};

/* CODICE GLOBALE */

getPizze();
loadData();
