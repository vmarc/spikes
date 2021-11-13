import { createFeatureSelector } from '@ngrx/store';
import { page6FeatureKey, Page6State } from './page6.state';

export const selectPage6State = createFeatureSelector<Page6State>(page6FeatureKey);
