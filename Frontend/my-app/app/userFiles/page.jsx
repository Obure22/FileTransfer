import '@/app/ui/files.css';
import FormUpload from './FormUpload.jsx';
import FilesList from './FilesList.jsx';

export const metadata = {
    title: 'Файлы пользователя',
    description: 'Это страница файлов пользователя'
};

export default function FilesPage() {




  return (
      <main>
          <FormUpload/>
          <FilesList/>
      </main>

  );
}
