import React, { useState } from 'react';
import { Form, Input, Button, Alert, Card, Typography, Divider } from 'antd';
import { useGoogleLogin } from '@react-oauth/google';
import { GoogleOutlined } from '@ant-design/icons';
// import axios from 'axios';

const { Link, Text } = Typography;

const Login = () => {
    const [error, setError] = useState('');

    const handleLogin = async (values) => {
        // try {
        //     const response = await axios.post('http://localhost:8080/login', {
        //         username: values.username,
        //         password: values.password,
        //     });
        //     alert(response.data); // Handle successful login
        //     setError('');
        // } catch (err) {
        //     setError('Invalid credentials');
        // }
    };

    const login = useGoogleLogin({
        onSuccess: codeResponse => console.log(codeResponse),
        flow: 'auth-code',
    });

    return (
        <div style={styles.container}>
            <Card title="User Login" style={styles.card}>
                {error && <Alert message={error} type="error" style={{ marginBottom: 16 }} />}
                <Form
                    name="loginForm"
                    onFinish={handleLogin}
                    layout="vertical"
                >
                    <Form.Item
                        label="Username"
                        name="username"
                        rules={[{ required: true, message: 'Please input your username!' }]}
                    >
                        <Input placeholder="Enter your username" />
                    </Form.Item>

                    <Form.Item
                        label="Password"
                        name="password"
                        rules={[{ required: true, message: 'Please input your password!' }]}
                    >
                        <Input.Password placeholder="Enter your password" />
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" htmlType="submit" block>
                            Login
                        </Button>
                    </Form.Item>
                    <div style={{ textAlign: 'center' }}>
                        <Divider style={{ borderColor: 'black', fontSize: '10px' }} >
                            <Text style={{ fontSize: '10px' }}>Don't have an account? </Text>
                            <Link href="/register" style={{ fontSize: '10px' }}>Register now</Link>
                        </Divider>
                    </div>
                    <div style={{ textAlign: 'center' }}>
                        <Button onClick={() => login()}><GoogleOutlined />Sign in with google</Button>
                    </div>

                </Form>
            </Card>
        </div>
    );
};

const styles = {
    container: {
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "#f0f2f5",
        padding: '15px'
    },
    card: {
        width: 350,
        boxShadow: '0 4px 12px rgba(0,0,0,0.15)',
        borderRadius: 8,
    },
};

export default Login;

