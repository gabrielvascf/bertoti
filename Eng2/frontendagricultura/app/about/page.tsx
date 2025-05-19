import React from 'react';

export default function AboutPage() {
  return (
    <div className="container mx-auto p-8">
      <h1 className="text-4xl font-bold mb-6">Sobre o Sistema de Monitoramento de Agricultura de Precisão</h1>
      <p className="text-lg mb-4">
        Nosso Sistema de Monitoramento de Agricultura de Precisão foi desenvolvido para ajudar os agricultores a otimizar 
        a produção de suas culturas, gerenciar recursos de forma eficiente e melhorar a sustentabilidade de suas práticas agrícolas. 
        Ao aproveitar tecnologias avançadas de IoT, sensoriamento remoto e análise de dados, capacitamos decisões baseadas em dados 
        que aumentam a produtividade e reduzem o impacto ambiental.
      </p>
      <h2 className="text-2xl font-semibold mt-6 mb-4">Nossa Missão</h2>
      <p className="text-lg mb-4">
        Nossa missão é apoiar a segurança alimentar global ao empoderar os agricultores com tecnologias de ponta. 
        Acreditamos que a gestão eficiente de recursos e as técnicas de agricultura de precisão são fundamentais para obter 
        maiores rendimentos e reduzir o desperdício, o que levará a um futuro agrícola mais sustentável.
      </p>
      <h2 className="text-2xl font-semibold mt-6 mb-4">Principais Funcionalidades</h2>
      <ul className="list-disc list-inside text-lg mb-4">
        <li>Monitoramento em tempo real de solo e culturas com dispositivos IoT</li>
        <li>Análise de dados para otimização de irrigação, fertilização e controle de pragas</li>
        <li>Plataforma intuitiva para visualização de dados e apoio à tomada de decisões</li>
        <li>Soluções escaláveis para apoiar fazendas de diversos tamanhos</li>
      </ul>
      <h2 className="text-2xl font-semibold mt-6 mb-4">Junte-se a Nós para Moldar o Futuro da Agricultura</h2>
      <p className="text-lg">
        Estamos constantemente trabalhando para aprimorar nosso sistema e incorporar os últimos avanços tecnológicos. 
        Junte-se a nós nesta jornada para tornar a agricultura mais eficiente, produtiva e sustentável para as futuras gerações.
      </p>
    </div>
  );
}
