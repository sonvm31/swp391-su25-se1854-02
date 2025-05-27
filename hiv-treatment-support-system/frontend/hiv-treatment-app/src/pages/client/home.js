import React from 'react';
import { Breadcrumb, Layout, Menu, theme } from 'antd';
import AppHeader from '../../components/AppHeader';
import AppFooter from '../../components/AppFooter';
const { Header, Content, Footer } = Layout;
// const items = Array.from({ length: 15 }).map((_, index) => ({
//     key: index + 1,
//     label: `nav ${index + 1}`,
// }));
const Home = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout>
            <AppHeader />
            <Content style={{ padding: '0 20px' }}>
                <Breadcrumb
                    style={{ margin: '16px 0' }}
                    items={[{ title: 'Home' }, { title: 'List' }, { title: 'Home' }]}
                />
                <div
                    style={{
                        background: colorBgContainer,
                        minHeight: 280,
                        padding: 24,
                        borderRadius: borderRadiusLG,
                    }}
                >
                    Content
                </div>
            </Content>
            <AppFooter />
        </Layout>
    );
};
export default Home;