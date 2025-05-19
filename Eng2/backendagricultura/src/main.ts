import express from 'express';
import cors from 'cors';

const app = express();
const PORT = 3001;

// Middleware
app.use(cors());
app.use(express.json());

type Network = {
  id: string;
  name: string;
  devices: Device[]
};

type Device = {
  title: string;
  description: string;
  location: string;
  dataHistory: any[];
  lastData: any;
}

let networks: Network[] = [
  {
    id: '1',
    name: 'Rede de Sensores',
    devices: [
      {
        title: 'Sensor de humidade do solo',
        description: 'Mede a humidade do solo para gerenciar a irrigação.',
        location: 'Campo A - Seção Norte',
        dataHistory: [],
        lastData: '34%'
      },
      {
        title: 'Sensor de temperatura e humidade',
        description: 'Monitora temperatura do ar e níveis de humidade para crescimento ideal de colheita.',
        location: 'Estufa 2',
        dataHistory: [],
        lastData: '22°C, 60% humidade'
      },
      {
        title: 'Sensor de pH do solo',
        description: 'Mede os níveis de pH do solo para garantir a disponibilidade ideal de nutrientes.',
        location: 'Campo B - Seção Oeste',
        dataHistory: [],
        lastData: '6.5 pH'
      },
      {
        title: 'Estação Meteorológica',
        description: 'Coleta dados sobre precipitação, velocidade do vento e exposição ao sol.',
        location: 'Campo Principal - Centro',
        dataHistory: [],
        lastData: '10mm de chuva, vento a 15 km/h'
      }
    ]
  }
];

// Routes
app.get('/api/networks', (req, res) => {
  // Example data, replace with your actual data fetching logic
  res.json(networks);
});
app.get('/api/networks/:id', (req, res) => {
  const { id } = req.params;
  const network = networks.find((network) => network.id === id);

  if (network) {
    res.json(network);
  } else {
    res.status(404).json({ message: 'Network not found' });
  }
});

app.listen(PORT, () => {
  console.log(`Server started on http://localhost:${PORT}`);
});
