const input = document.getElementById("selectAvatar");
const avatar = document.getElementById("avatar");
const textArea = document.getElementById("textAreaExample");


const getAnimals = async (event) => {
    const url = "http://localhost:6789/animal"
    return await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/xml',
            'Accept': 'application/xml',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods':'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token',
        }

    }).then(response => response.text())
    .then(str => new window.DOMParser().parseFromString(str, "text/xml"))
    .then(data => data);
    

    
      
};

async function deleteAnimals (id) {
  const url = `http://localhost:6789/animal/delete/${id}`
  return await fetch(url, {
      method: 'GET',
      headers: {
          'Content-Type': 'application/xml',
          'Accept': 'application/xml',
          'Access-Control-Allow-Headers': '*',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods':'GET, POST, PATCH, PUT, DELETE, OPTIONS',
          'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token',
      }

  }).then(response => {
    document.location.reload()
    return response.text()
  }) 


 
    
};


function xml2json(xml) {
    try {
      var obj = {};
      if (xml.children.length > 0) {
        for (var i = 0; i < xml.children.length; i++) {
          var item = xml.children.item(i);
          var nodeName = item.nodeName;
  
          if (typeof (obj[nodeName]) == "undefined") {
            obj[nodeName] = xml2json(item);
          } else {
            if (typeof (obj[nodeName].push) == "undefined") {
              var old = obj[nodeName];
  
              obj[nodeName] = [];
              obj[nodeName].push(old);
            }
            obj[nodeName].push(xml2json(item));
          }
        }
      } else {
        obj = xml.textContent;
      }
      return obj;
    } catch (e) {
        console.log(e.message);
    }
  }

function createAnimalsList(animals){
  animals.map((animal) => {
    const divLista = document.getElementById('lista');
  
    const cartaoAnimal = document.createElement('div');
    cartaoAnimal.className = "cartaoAnimal"
    
    const nomeAnimal = document.createElement('p');
    const idadeAnimal = document.createElement('p');
    const porteAnimal = document.createElement('p');
    const especieAnimal = document.createElement('p');
    const localAnimal = document.createElement('p');
    const contatoAnimal = document.createElement('p');

    nomeAnimal.append(`Nome: ${animal.nome}`)
    idadeAnimal.append(`Idade: ${animal.idade}`)
    porteAnimal.append(`Porte: ${animal.porte}`)
    especieAnimal.append(`Especie: ${animal.especie}`)
    localAnimal.append(`Local: ${animal.local}`)
    contatoAnimal.append(`Contato: ${animal.contato}`)

    cartaoAnimal.append(nomeAnimal)
    cartaoAnimal.append(idadeAnimal)
    cartaoAnimal.append(porteAnimal)
    cartaoAnimal.append(especieAnimal)
    cartaoAnimal.append(localAnimal)
    cartaoAnimal.append(contatoAnimal)
 
    const botao = document.createElement("button")    
    botao.append(`Adotar Animal`)
    botao.onclick = deleteAnimals(animal.id)
    cartaoAnimal.append(botao)


    divLista.append(cartaoAnimal)
  })
}