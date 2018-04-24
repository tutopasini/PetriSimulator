# PetriSimulator
Simulador de Redes de Petri

Para rodar o projeto, basta executar o .JAR

A entrada deve ser informada em formato JSON. 

Exemplo de entrada:

{
  "lugares": [
    
    {
      "label":"L1",    -> Obrigatório
      "marcas":2,      -> Opcional, irá presumir 0 se não informado
      "tempo":3        -> Opcional, irá presumir 0 se não informado
    },
    
    {
      "label":"L2"
    },
    {
      "label":"L3",
      "marcas":0,
      "tempo":0
    },
    {
      "label":"L4",
      "marcas":0,
      "tempo":0
    }
  ],
  
  "transicoes":[
    
    {
      "label":"T1"      -> Obrigatório
    },
    {
      "label":"T2"
    },
    {
      "label":"T3"
    }
    
  ],
  
  "arcos":[
    
    {
      "entrada":"L1",    -> Obrigatório
      "saida":"T1",      -> Obrigatório
      "peso":1           -> Opcional, irá presumir peso 1 se não informado
    },

    {
      "entrada":"L1",
      "saida":"T2",
      "peso":1
    },
    
    { 
      "entrada":"T1",
      "saida":"L2",
      "peso":1
    },
    { 
      "entrada":"T2",
      "saida":"L3",
      "peso":1
    },
    {
      "entrada":"L3",
      "saida":"T3",
      "peso":1
    },
    {
      "entrada":"T3",
      "saida":"L4",
      "peso":1
    },
    {
    "entrada":"T3",
    "saida":"L1",
    "peso":1
    },
  ]
  }
