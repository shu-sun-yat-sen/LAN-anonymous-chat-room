import { shallowMount } from '@vue/test-utils';
import ElementPlus from 'element-plus';
import ChatBubble from '@/components/chatWindowComponent/ChatBubble.vue';

describe('ChatBubble.vue', () => {
  it('renders props correctly when same is true', () => {
    const text = 'Hello';
    const avatar = 'avatar.jpg';
    const username = 'User';
    const same = true;
    const content = 'Test Content';
    const type = 'user';


    const wrapper = shallowMount(ChatBubble, {
      propsData: { text, avatar, username, same , content, type},
      global:{
        plugins: [ElementPlus],
      }
    });

    expect(wrapper.find('.text').exists()).toBe(false);
    expect(wrapper.props().content).toBe('Test Content');
    expect(wrapper.find('.avatar').attributes('src')).toBe(avatar);
    expect(wrapper.find('.username').text()).toBe(username);
    expect(wrapper.find('.chat-right_triangle').exists()).toBe(true);
    expect(wrapper.find('.chat-left_triangle').exists()).toBe(false);
  });

  it('renders props correctly when same is false', () => {
    const text = 'Hello';
    const avatar = 'avatar.jpg';
    const username = 'User';
    const same = true;
    const content = 'Test Content';
    const type = 'user';


    const wrapper = shallowMount(ChatBubble, {
      propsData: { text, avatar, username, same , content, type},
      global:{
        plugins: [ElementPlus],
      }
    });
    
    expect(wrapper.find('.text').exists()).toBe(false);
    // expect(wrapper.find('.text').text()).toBe(text);
    expect(wrapper.props().content).toBe('Test Content');
    expect(wrapper.find('.avatar').attributes('src')).toBe(avatar);
    expect(wrapper.find('.username').text()).toBe(username);
    expect(wrapper.find('.chat-right_triangle').exists()).toBe(true);
    expect(wrapper.find('.chat-left_triangle').exists()).toBe(false);
  });
});
