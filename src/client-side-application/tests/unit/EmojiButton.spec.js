import { shallowMount } from '@vue/test-utils';
import EmojiButton from '@/components/inputWindowComponent/EmojiButton.vue'; // 请将路径调整为你的组件实际路径
import EmojiWindow from '@/components/inputWindowComponent/EmojiWindow.vue';

describe('EmojiButton.vue', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(EmojiButton);
  });

  it('should render a button', () => {
    expect(wrapper.find('button.button').exists()).toBe(true);
  });

  it('should toggle EmojiWindow visibility when button is clicked', async () => {
    const button = wrapper.find('button.button');
    expect(wrapper.findComponent(EmojiWindow).exists()).toBe(false);

    await button.trigger('click');
    expect(wrapper.findComponent(EmojiWindow).exists()).toBe(true);

    await button.trigger('click');
    expect(wrapper.findComponent(EmojiWindow).exists()).toBe(false);
  });

  it('should emit "send-emoji" event and close EmojiWindow when an emoji is selected', async () => {
    await wrapper.setData({ showEmojiWindow: true });
    const emojiWindow = wrapper.findComponent(EmojiWindow);
    
    emojiWindow.vm.$emit('select-emoji', '😊');

    expect(wrapper.emitted('send-emoji')).toBeTruthy();
    expect(wrapper.emitted('send-emoji')[0]).toEqual(['😊']);
    expect(wrapper.vm.showEmojiWindow).toBe(false);
  });
});
