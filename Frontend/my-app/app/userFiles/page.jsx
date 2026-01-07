import '@/app/ui/files.css';
import FileManager from './FileManager.jsx';

export const metadata = {
    title: 'Файлы пользователя',
    description: 'Это страница файлов пользователя'
};

export default function FilesPage() {
  return (
      <main>
          <FileManager></FileManager>
      </main>
  );
}
