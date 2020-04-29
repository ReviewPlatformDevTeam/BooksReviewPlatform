import React from 'react';
import { mount } from 'enzyme';
import { PasswordReset } from "../PasswordReset/PasswordReset";

describe('PasswordReset', () => {

    let wrapper;

    beforeEach(() => {
        wrapper = mount(<PasswordReset/>);
        const push = jest.fn();
        wrapper.setProps({ history: { push } });

        wrapper.setState({
            email: "test@email.com"
        });
        global.alert = jest.fn(value => {return value});
    });

    afterEach(() => {
        wrapper.unmount();
    });

    test('should call reset function with email and display alert about success', async () => {
        const mockFetchPromise = Promise.resolve({type: 'opaque'});
        global.fetch = jest.fn().mockImplementation(() => mockFetchPromise);

        await wrapper.instance().resetHandler({preventDefault: jest.fn()});
        expect(global.alert).toHaveBeenCalledTimes(1);
        expect(global.alert).toHaveLastReturnedWith("Reset link sent to test@email.com");
    });

    test('should call reset function with email and display alert about fail', async () => {
        const mockFetchPromise = Promise.resolve({type: 'not ok'});
        global.fetch = jest.fn().mockImplementation(() => mockFetchPromise);

        await wrapper.instance().resetHandler({preventDefault: jest.fn()});
        expect(global.alert).toHaveBeenCalledTimes(1);
        expect(global.alert).toHaveLastReturnedWith("Error during reset");
    });

    test('should call reset function with email and display alert about fail when status 404', async () => {
        const mockFetchPromise = Promise.resolve({type: 'not ok', status: '404'});
        global.fetch = jest.fn().mockImplementation(() => mockFetchPromise);

        await wrapper.instance().resetHandler({preventDefault: jest.fn()});
        expect(global.alert).toHaveBeenCalledTimes(1);
        expect(global.alert).toHaveLastReturnedWith("Page not found");
    });

});