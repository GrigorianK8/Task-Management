import { ThemeProvider } from '@mui/material'
import { darkTheme } from './theme/darktheme'
import Navbar from './components/navbar/Navbar'
import Home from './components/home/Home'
import Auth from './components/auth/Auth'

function App() {
	return (
		<ThemeProvider theme={darkTheme}>
			{/* <Navbar /> */}
			{/* <Home /> */}
			<Auth />
		</ThemeProvider>
	)
}

export default App
