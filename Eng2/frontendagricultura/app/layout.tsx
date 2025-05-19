import './globals.css';
import { ReactNode } from 'react';
import Link from 'next/link';

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="en">
      <body>
        <header className="bg-gray-800 text-white p-4">
          <nav className="container mx-auto flex justify-between items-center">
            <Link href="/" className="text-2xl font-bold">
              Sistema de Monitoramento de Agricultura de Precisão
            </Link>
            <div className="space-x-6">
              <Link href="/" className="hover:text-gray-400">Início</Link>
              <Link href="/about" className="hover:text-gray-400">Sobre</Link>
            </div>
          </nav>
        </header>
        <main>{children}</main>
      </body>
    </html>
  );
}
