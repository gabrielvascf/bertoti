"use client"
import Link from 'next/link';
import { useEffect, useState } from 'react';

export default function HomePage() {
  const [networks, setNetworks] = useState<{ id: string; name: string }[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchNetworks = async () => {
      try {
        const response = await fetch('http://localhost:3001/api/networks'); // Adjust the URL to your API endpoint
        const data = await response.json();
        setNetworks(data);
      } catch (error) {
        console.error('Failed to fetch networks:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchNetworks();
  }, []);

  if (loading) {
    return <div className="container mx-auto p-8">Carregando...</div>;
  }

  return (
    <div className="container mx-auto p-8">
      <h1 className="text-4xl font-bold text-center mb-6">Bem vindo ao SMAG</h1>
      <p className="text-xl text-center mb-6">Selecione uma rede para modificar dispositivos:</p>
      
      <ul className="space-y-4">
        {networks.map((network) => (
          <li key={network.id} className="flex justify-center">
            <Link
              href={`/${network.id}`}
              className="text-xl text-blue-500 hover:text-blue-700 font-semibold"
            >
              {network.name}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
