import '@/app/ui/auth.css';
import FormRegister from "./FormRegister";

export const metadata = {
    title: 'Регистрация',
    description: 'Это страница регистрации пользователя'
};

export default function RegisterPage() {

    return (
        <div>
            <main>
                <FormRegister></FormRegister>
            </main>
        </div>
    );
}
