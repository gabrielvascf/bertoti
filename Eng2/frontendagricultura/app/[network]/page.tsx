"use client";
import { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import { ChevronDownIcon, ChevronUpIcon } from '@heroicons/react/24/outline';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCloud, faDroplet, faTemperatureThreeQuarters, faFlask } from '@fortawesome/free-solid-svg-icons';

const iconMapping: Record<string, JSX.Element> = {
  'Sensor de humidade do solo': <FontAwesomeIcon icon={faDroplet} className='h-6 w-6 text-blue-600' />,
  'Sensor de temperatura e humidade': <FontAwesomeIcon icon={faTemperatureThreeQuarters} className='h-6 w-6 text-yellow-500' />,
  'Sensor de pH do solo': <FontAwesomeIcon icon={faFlask} className='h-6 w-6 text-green-500' />,
  'Estação Meteorológica': <FontAwesomeIcon icon={faCloud} className='h-6 w-6 text-gray-700' />
};

type Device = {
  title: string
  description: string;
  location: string;
  dataHistory: any[]
  lastData: any;
  icon: JSX.Element;
};

export default function NetworkPage() {
  const { network } = useParams();
  const [currentNetwork, setCurrentNetwork] = useState<string | null>();
  const [expandedDevice, setExpandedDevice] = useState<string | null>(null);
  const [devices, setDevices] = useState<Device[]>([]);

  useEffect(() => {
    const fetchDevices = async () => {
      try {
        const response = await fetch(`http://localhost:3001/api/networks/${network}`);
        const data = await response.json();

        // Preprocess the devices to include the appropriate icons
        const devicesWithIcons = data.devices.map((device: Omit<Device, 'icon'>) => ({
          ...device,
          icon: iconMapping[device.title] || <span className="h-6 w-6 text-gray-400">?</span>, // Fallback icon
        }));

        setDevices(devicesWithIcons);
        setCurrentNetwork(data.name);
      } catch (error) {
        console.error('Failed to fetch devices', error);
      }
    };

    fetchDevices();
  }, [network]);

  const toggleDevice = (id: string) => {
    setExpandedDevice((prev) => (prev === id ? null : id));
  };

  return (
    <div className="container mx-auto p-8">
      <h2 className="text-3xl font-bold mb-4">Gerenciar dispositivos na rede {currentNetwork}</h2>
      <p className="text-xl mb-6">Dispositivos conectados:</p>
      
      <div className="space-y-4">
        {devices.map((device) => (
          <div key={device.title} className="border border-gray-300 rounded-lg p-4 transition-all duration-300">
            <div className="flex items-center justify-between cursor-pointer" onClick={() => toggleDevice(device.title)}>
              <div className="flex items-center space-x-3">
                {/* Icon slot */}
                {device.icon}
                <span className="text-lg font-semibold text-blue-600">{device.title}</span>
              </div>
              <div>
                {/* Expand/Collapse Icon */}
                {expandedDevice === device.title ? (
                  <ChevronUpIcon className="h-5 w-5 text-gray-600" />
                ) : (
                  <ChevronDownIcon className="h-5 w-5 text-gray-600" />
                )}
              </div>
            </div>
            
            {/* Expandable content with animation */}
            <div
              className={`overflow-hidden transition-[max-height] duration-300 ${
                expandedDevice === device.title ? 'max-h-96' : 'max-h-0'
              }`}
            >
              {expandedDevice === device.title && (
                <div className="mt-2 text-gray-700">
                  <p><strong>Descrição:</strong> {device.description}</p>
                  <p><strong>Local:</strong> {device.location}</p>
                  <p><strong>Última medição:</strong> {device.lastData}</p>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
